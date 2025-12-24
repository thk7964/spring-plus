package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.example.expert.domain.comment.entity.QComment.comment;
import static org.example.expert.domain.manager.entity.QManager.manager;
import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class TodoCustomRepositoryImpl implements TodoCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {

        return Optional.ofNullable(queryFactory
                .selectFrom(todo)
                .leftJoin(todo.user)
                .fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne());

    }

    @Override
    public Page<TodoSearchResponse> searchTodoByMultiCondition(String titleKeyword, String nickname, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        List<TodoSearchResponse> content =
                queryFactory
                        .select(Projections.constructor(
                                TodoSearchResponse.class,
                                todo.title,
                                manager.id.countDistinct(),
                                comment.id.countDistinct()
                        ))
                        .from(todo)
                        .leftJoin(todo.managers, manager)
                        .leftJoin(todo.comments, comment)
                        .leftJoin(manager.user, user)
                        .where(
                                titleContains(titleKeyword),
                                nicknameContains(nickname),
                                createBetween(start, end)
                        )
                        .groupBy(todo.id)
                        .orderBy(todo.createdAt.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        Long total =
                queryFactory
                        .select(todo.count())
                        .from(todo)
                        .where(
                                titleContains(titleKeyword),
                                nicknameContains(nickname),
                                createBetween(start, end)
                        )
                        .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }


    private BooleanExpression todoIdCondition(Long todoId) {
        return todoId != null ? todo.id.eq(todoId) : null;
    }
    private BooleanExpression titleContains(String titleKeyword) {
        return titleKeyword != null ? todo.title.contains(titleKeyword) : null;
    }
    private BooleanExpression nicknameContains(String nickname) {
        return nickname != null ? user.nickname.contains(nickname) : null;
    }
    private BooleanExpression createBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end ==null) {
            return null;
        }
        return todo.createdAt.between(start, end);
    }
}

package net.alexandrepaulkouame.exceptions;

import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GraphqlExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(java.lang.Throwable ex, graphql.schema.DataFetchingEnvironment env) {
        return GraphQLError.newError().message(ex.getMessage()).build();
    }

}

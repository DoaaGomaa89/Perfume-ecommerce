package com.gmail.merikbest2015.ecommerce.service.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    public GraphQL getGraphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws Exception {
        // Load schema from classpath inside the jar
        String sdl = loadResourceAsString("/graphql/schemas.graphql");

        GraphQLSchema schema = SchemaParser.newParser()
                .schemaString(sdl)
                .build()
                .makeExecutableSchema();

        this.graphQL = GraphQL.newGraphQL(schema).build();
    }

    private String loadResourceAsString(String path) throws Exception {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalStateException("Schema not found on classpath: " + path);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                return br.lines().collect(Collectors.joining("\n"));
            }
        }
    }
}

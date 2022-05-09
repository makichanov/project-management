package com.makichanov.projectmanagement.repository.specifier;

import com.makichanov.projectmanagement.repository.specifier.impl.ProjectDescriptionQuerySpecifierChain;
import com.makichanov.projectmanagement.repository.specifier.impl.ProjectNameQuerySpecifierChain;
import com.makichanov.projectmanagement.repository.specifier.impl.ProjectsPagingQuerySpecifierChain;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;

@Component
public class QueryBuilder {

    private final LinkedList<QuerySpecifierChain> chains = new LinkedList<>();
    private final LinkedList<QuerySpecifier> specifiers = new LinkedList<>();

    private QueryBuilder() {
        var descriptionSpecifier = new ProjectDescriptionQuerySpecifierChain();
        var nameSpecifier = new ProjectNameQuerySpecifierChain();
        var pagingSpecifier = new ProjectsPagingQuerySpecifierChain();
        Collections.addAll(specifiers, descriptionSpecifier, nameSpecifier, pagingSpecifier);
        for (QuerySpecifier specifier : specifiers) {
            var chain = new QuerySpecifierChain(specifier);
            chains.addLast(chain);
        }
        for (int i = 0; i < chains.size() - 1; i++) {
            QuerySpecifierChain nextChain = chains.get(i + 1);
            chains.get(i).setNextChain(nextChain);
        }
        chains.getLast().setNextChain(new QuerySpecifierChain(null));
    }

    public QuerySpecifierChain getChain() {
        return chains.getFirst();
    }

}

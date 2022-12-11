package com.findwise;

import java.util.Objects;

public class TermInDocument {

    private final String term;

    private final String documentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TermInDocument that = (TermInDocument) o;
        return term.equals(that.term) && documentId.equals(that.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term, documentId);
    }

    public TermInDocument(String term, String documentId) {
        this.term = term;
        this.documentId = documentId;
    }
}

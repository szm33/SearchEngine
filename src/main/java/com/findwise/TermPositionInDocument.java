package com.findwise;

public class TermPositionInDocument {

    private final String documentId;
    private final int termPosition;

    public String getDocumentId() {
        return documentId;
    }

    public int getTermPosition() {
        return termPosition;
    }

    public TermPositionInDocument(String documentId, int termPosition) {
        this.documentId = documentId;
        this.termPosition = termPosition;
    }
}
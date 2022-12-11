package com.findwise;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngineImpl implements SearchEngine {

    Map<String, List<TermPositionInDocument>> termPositionInDocumentByTerm = new HashMap<>();
    Map<TermInDocument, Double> tfByTermInDocument = new HashMap<>();
    Map<String, Double> idfByTerm = new HashMap<>();
    int amountOfDocuments = 0;

    @Override
    public void indexDocument(String id, String content) {
        amountOfDocuments++;
        String[] terms = content.split(" ");
        for (int i = 0; i < terms.length; i++) {
            String term = terms[i];
            if (termPositionInDocumentByTerm.containsKey(term)) {
                termPositionInDocumentByTerm.get(term).add(new TermPositionInDocument(id, i));
            }
            else {
                termPositionInDocumentByTerm.put(term, new ArrayList<>(List.of(new TermPositionInDocument(id, i))));
            }
        }

        for (String term : terms) {
            tfByTermInDocument.put(new TermInDocument(term, id), termAmountInDocument(term, id) / terms.length);
        }

        termPositionInDocumentByTerm.keySet().forEach(term ->
            idfByTerm.put(term, Math.log(amountOfDocuments / amountOfDocumentWithTerm(term))));
    }

    private double termAmountInDocument(String term, String documentId) {
        return termPositionInDocumentByTerm.get(term).stream()
                .filter(termPositionInDocument -> termPositionInDocument.getDocumentId().equals(documentId))
                .count();
    }

    private double amountOfDocumentWithTerm(String term) {
        return termPositionInDocumentByTerm.get(term).stream()
                .map(TermPositionInDocument::getDocumentId)
                .distinct()
                .count();
    }

    private Set<String> getDocumentsWithTerm(String term) {
        return termPositionInDocumentByTerm.get(term).stream()
                .map(TermPositionInDocument::getDocumentId)
                .collect(Collectors.toSet());
    }

    @Override
    public List<IndexEntry> search(String term) {
        return getDocumentsWithTerm(term).stream()
                .map(documentId -> new IndexEntryResult(documentId, getScoreForDocument(term, documentId)))
                .sorted(Comparator.comparingDouble(IndexEntry::getScore).reversed())
                .collect(Collectors.toList());
    }

    private double getScoreForDocument(String term, String documentId) {
        return tfByTermInDocument.get(new TermInDocument(term, documentId)) * idfByTerm.get(term);
    }
}
package com.findwise;

public class IndexEntryResult implements IndexEntry{
    public IndexEntryResult(String id, double score) {
        this.id = id;
        this.score = score;
    }

    private String id;

    private double score;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public double getScore() {
        return score;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "IndexEntryImpl{" +
                "id='" + id + '\'' +
                ", score=" + score +
                '}';
    }
}

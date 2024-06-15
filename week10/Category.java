public enum Category {
    Professor("교수"),
    Assistant("조교"),
    Under("학부"),
    MS("석사"),
    PhD("박사");

    private String name;

    private Category(String name) {
        this.name = name;
    }

    public String getCategory() {
        return name;
    }
}

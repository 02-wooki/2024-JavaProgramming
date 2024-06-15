public enum Dept {
    Software("소프트웨어학과"),
    Computer("컴퓨터공학과"),
    Mechatronics("메카트로닉스공학과"),
    City("도시계획학과"),
    Civil("토목공학과");

    private String name;
    private Dept(String name) {
        this.name = name;
    }
    public String getDept() {
        return name;
    }
}

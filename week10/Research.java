public enum Research {
    Security("정보보안"),
    Network("네트워크"),
    Database("데이터베이스"),
    Ai("인공지능"),
    MachineLearning("머신러닝"),
    DataAnalitics("데이터분석"),
    IoT("사물인터넷"),
    Metaverse("가상현실"),
    CloudComputing("클라우드컴퓨팅"),
    HumanLanguage("자연어 처리");

    public String name;
    private Research(String name) {
        this.name = name;
    }
    public String getResearch() {
        return name;
    }
}

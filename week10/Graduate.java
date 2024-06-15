public class Graduate extends Student {
    Research field;

    Graduate(String type, String number, String name, String birthday, String phone, String address) {
        super(type, number, name, birthday, phone, address);
        setResearchField();
    }
    private void setResearchField() {
        switch (Rand.r(0, 9)) {
            case 0:
                this.field = Research.Security;
                break;
            case 1:
                this.field = Research.Network;
                break;
            case 2:
                this.field = Research.Database;
                break;
            case 3:
                this.field = Research.Ai;
                break;
            case 4:
                this.field = Research.MachineLearning;
                break;
            case 5:
                this.field = Research.DataAnalitics;
                break;
            case 6:
                this.field = Research.IoT;
                break;
            case 7:
                this.field = Research.Metaverse;
                break;
            case 8:
                this.field = Research.CloudComputing;
                break;
            case 9:
                this.field = Research.HumanLanguage;
        }
    }

    @Override
    public String toString() {
        return String.format("%s, 연구분야: %s", super.toString(), field.getResearch());
    }
}

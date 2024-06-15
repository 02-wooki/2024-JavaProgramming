public class Professor extends Staff{
    Research field;
    public String labLocate;

    Professor(String type, String number, String name, String birthday, String phone, String address) {
        super(type, number, name, birthday, phone, address);
        setField();
        setLabLocate();
    }

    private void setField() {
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

    private void setLabLocate() {
        labLocate = String.format("%d호관 ", Rand.r(1, 30));
        int ho = Rand.r(101, 699);
        while (ho % 100 == 0 || ho % 100 > 30)
            ho = Rand.r(101, 699);
        labLocate += ho + "호실";
    }

    @Override
    public String toString() {
        return String.format("%s, 전공: %s, 연구실: %s", super.toString(), field.getResearch(), labLocate);
    }
}

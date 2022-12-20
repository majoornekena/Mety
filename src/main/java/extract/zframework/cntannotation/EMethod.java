package extract.zframework.cntannotation;

public enum EMethod {
    GET("GET"), POST("POST");

    String name;

    private EMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

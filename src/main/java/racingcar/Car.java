package racingcar;

public class Car {
    private String name;  // 자동차 이름
    private Integer pos;  // 자동차 위치

    public Car(String name) {
        this.name = name;
        this.pos = 0;
    }

    public String getName() {
        return name;
    }

    public Integer getPos() {
        return pos;
    }

    public void MovingCar(Integer count) {
        pos += count;
    }

    public void showCarPos() {
        String posHyphen = "-".repeat(pos);
        System.out.println(name + " : "  + posHyphen); // pobi : --
    }
}
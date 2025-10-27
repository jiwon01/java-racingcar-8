package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.*;

public class Game {
    public void startGame() {
        // 자동차 이름 목록 입력
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String userInput = Console.readLine();

        // 유효성 검증
        validateCarNames(userInput);

        // 자동차 목록 저장
        String[] carNames = userInput.split(",");

        System.out.println("시도할 횟수는 몇 회인가요?");
        Integer userCountInput = Integer.valueOf(Console.readLine());

        // 자동차 객체가 들어있는 배열
        Car[] cars = new Car[carNames.length];

        for (int i = 0; i < carNames.length; i++) {
            cars[i] = new Car(carNames[i]);
        }

        System.out.println("\n실행 결과");

        // 라운드
        for (int i = 0; i < userCountInput; i++) {
            startRound(cars);
            showRoundResult(cars);
            System.out.println();
        }

        // 최종 결과 출력
        showWinner(List.of(cars));

    }

    /**
     * 자동차 이름 입력 유효성 검증
     */
    private void validateCarNames(String input) {
        // 입력값이 비거나 null인 경우
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("입력값이 비었습니다");
        }

        String[] carNames = input.split("\\s*,\\s*");

        for (String name : carNames) {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("자동차 이름이 비었습니다");
            }
            if (name.length() > 5) {
                throw new IllegalArgumentException("자동차의 이름이 5자를 초과했습니다");
            }
        }
    }

    /**
     * 랜덤 수 뽑기
     */
    private Integer getRandomNumber() {
        return Randoms.pickNumberInRange(0, 9);
    }

    /**
     * 한 차수 라운드
     */
    private void startRound(Car[] cars) {
        for (Car car : cars) {
            car.movingCar(getRandomNumber());
        }
    }

    /**
     * 한 차수 라운드 실행 결과
     */
    private void showRoundResult(Car[] cars) {
        for (Car car : cars) {
            car.showCarPos();
        }
    }

    /**
     * 우승자 출력
     */
    private void showWinner(List<Car> cars) {
        int maxPos = findMaxPosition(cars);
        String winnerNames = getWinnerNames(cars, maxPos);
        System.out.println("최종 우승자 : " + winnerNames);
    }

    private int findMaxPosition(List<Car> cars) {  // depth 줄이기
        return cars.stream()
                .mapToInt(Car::getPos)
                .max()
                .orElse(0);
    }

    private String getWinnerNames(List<Car> cars, int maxPos) {  // depth 줄이기
        return cars.stream()
                .filter(car -> car.getPos() == maxPos)
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }
}
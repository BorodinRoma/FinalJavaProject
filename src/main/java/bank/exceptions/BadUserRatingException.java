package bank.exceptions;

public class BadUserRatingException extends Exception {
    public BadUserRatingException(Integer bankRating, Integer userRating) {
        super("Кредитный рейтинг пользователя недостаточно высок для выдачи кредита (рейтинг банка - "
                + bankRating + "; кредитный рейтинг пользователя - " + userRating + ")");
    }
}

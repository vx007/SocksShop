package pro.sky.socksshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Color {
    RED("Красный"),
    ORANGE("Оражевый"),
    YELLOW("Жёлтый"),
    GREEN("Зеленый"),
    BLUE("Синий"),
    VIOLET("Фиолетовый"),
    BLACK("Чёрный"),
    WHITE("Белый");
    private final String title;
}

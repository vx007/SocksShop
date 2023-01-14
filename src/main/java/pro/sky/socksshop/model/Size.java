package pro.sky.socksshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Size {
    S(35),
    M(37),
    L(39),
    XL(41),
    XXL(43),
    XXXL(45);
    private final int value;

    public static Size valueOf(int size) {
        for (Size element : Size.values()) {
            if (element.value == size) {
                return element;
            }
        }
        return null;
    }
}

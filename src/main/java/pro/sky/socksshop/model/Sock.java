package pro.sky.socksshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Sock {
    private Color color;
    private Size size;
    private int cottonPart;

    public Sock(Color color, int size, int cottonPart) {
        this.color = color;
        this.size = Size.valueOf(size);
        this.cottonPart = cottonPart;
    }
}

package pro.sky.socksshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.socksshop.exception.SockBadParamException;
import pro.sky.socksshop.model.Color;
import pro.sky.socksshop.model.Size;
import pro.sky.socksshop.model.Sock;
import pro.sky.socksshop.service.SockService;

@RestController
@RequestMapping(value = "/api/socks")
public class SockController {
    private final SockService sockService;
    private final String BADPARAMS = "Неверные параметры!";

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestParam Color color,
                                          @RequestParam int size,
                                          @RequestParam int cottonPart,
                                          @RequestParam int quantity) {
        validate(size, cottonPart, quantity);
        Sock sock = new Sock(color, size, cottonPart);
        int value = sockService.add(sock, quantity);
        return ResponseEntity.ok().body(value);
    }

    @GetMapping
    public ResponseEntity<Integer> read(@RequestParam Color color,
                                        @RequestParam int size,
                                        @RequestParam(required = false) Integer cottonMin,
                                        @RequestParam(required = false) Integer cottonMax) {
        if (size < Size.XXXS.getValue() || size > Size.XXXL.getValue()) {
            throw new SockBadParamException(BADPARAMS);
        }
        if (cottonMin == null && cottonMax == null) {
            throw new SockBadParamException(BADPARAMS);
        }
        if (cottonMin != null && cottonMax == null && cottonMin > 0 && cottonMin <= 100) {
            Sock sock = new Sock(color, size, cottonMin);
            int value = sockService.getMin(sock);
            return ResponseEntity.ok().body(value);
        }
        if (cottonMax != null && cottonMin == null && cottonMax > 0 && cottonMax <= 100) {
            Sock sock = new Sock(color, size, cottonMax);
            int value = sockService.getMax(sock);
            return ResponseEntity.ok().body(value);
        }
        return ResponseEntity.badRequest().build();
    }


    @PutMapping
    public ResponseEntity<Integer> update(@RequestParam Color color,
                                          @RequestParam int size,
                                          @RequestParam int cottonPart,
                                          @RequestParam int quantity) {
        validate(size, cottonPart, quantity);
        Sock sock = new Sock(color, size, cottonPart);
        int value = sockService.remove(sock, quantity);
        return ResponseEntity.ok().body(value);
    }

    @DeleteMapping
    public ResponseEntity<Integer> delete(@RequestParam Color color,
                                          @RequestParam int size,
                                          @RequestParam int cottonPart,
                                          @RequestParam int quantity) {
        validate(size, cottonPart, quantity);
        Sock sock = new Sock(color, size, cottonPart);
        int value = sockService.remove(sock, quantity);
        return ResponseEntity.ok().body(value);
    }

    private void validate(int size, int cottonPart, int quantity) {
        if (size < Size.XXXS.getValue() || size > Size.XXXL.getValue() || cottonPart < 0 || cottonPart > 100 || quantity <= 0) {
            throw new SockBadParamException(BADPARAMS);
        }
    }
}

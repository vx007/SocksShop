package pro.sky.socksshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.socksshop.model.Color;
import pro.sky.socksshop.model.Size;
import pro.sky.socksshop.model.Sock;
import pro.sky.socksshop.service.SockService;

@RestController
@RequestMapping(value = "/api/socks")
public class SockController {

    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestParam Color color,
                                          @RequestParam int size,
                                          @RequestParam int cottonPart,
                                          @RequestParam int quantity) {
        if (size < Size.S.getValue() || size > Size.XXXL.getValue() || cottonPart < 0 || cottonPart > 100 || quantity <= 0) {
            return ResponseEntity.badRequest().build();
        }
        int value = sockService.add(new Sock(color, size, cottonPart), quantity);
        return ResponseEntity.ok().body(value);
    }

    @GetMapping
    public ResponseEntity<Integer> read(@RequestParam Color color,
                                        @RequestParam int size,
                                        @RequestParam(required = false) Integer cottonMin,
                                        @RequestParam(required = false) Integer cottonMax) {
        if (size < Size.S.getValue() || size > Size.XXXL.getValue()) {
            return ResponseEntity.badRequest().build();
        }
        if (cottonMin == null && cottonMax == null) {
            return ResponseEntity.badRequest().build();
        }
        if (cottonMin != null && cottonMax == null && cottonMin > 0 && cottonMin <= 100) {
            int value = sockService.getMin(new Sock(color, size, cottonMin));
            return ResponseEntity.ok().body(value);
        }
        if (cottonMax != null && cottonMin == null && cottonMax > 0 && cottonMax <= 100) {
            int value = sockService.getMax(new Sock(color, size, cottonMax));
            return ResponseEntity.ok().body(value);
        }
        return ResponseEntity.badRequest().build();
    }


    @PutMapping
    public ResponseEntity<Integer> update(@RequestParam Color color,
                                          @RequestParam int size,
                                          @RequestParam int cottonPart,
                                          @RequestParam int quantity) {
        if (size < Size.S.getValue() || size > Size.XXXL.getValue() || cottonPart < 0 || cottonPart > 100 || quantity <= 0) {
            return ResponseEntity.badRequest().build();
        }
        int value = sockService.remove(new Sock(color, size, cottonPart), quantity);
        return ResponseEntity.ok().body(value);
    }

    @DeleteMapping
    public ResponseEntity<Integer> delete(@RequestParam Color color,
                                          @RequestParam int size,
                                          @RequestParam int cottonPart,
                                          @RequestParam int quantity) {
        if (size < Size.S.getValue() || size > Size.XXXL.getValue() || cottonPart < 0 || cottonPart > 100 || quantity <= 0) {
            return ResponseEntity.badRequest().build();
        }
        int value = sockService.remove(new Sock(color, size, cottonPart), quantity);
        return ResponseEntity.ok().body(value);
    }
}

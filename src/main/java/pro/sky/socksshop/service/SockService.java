package pro.sky.socksshop.service;

import pro.sky.socksshop.model.Sock;

public interface SockService {
    int add(Sock sock, Integer quantity);

    int remove(Sock sock, Integer quantity);

    int getMin(Sock sock);

    int getMax(Sock sock);
}

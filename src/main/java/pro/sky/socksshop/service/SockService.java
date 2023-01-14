package pro.sky.socksshop.service;

import pro.sky.socksshop.model.Sock;

public interface SockService {
    int add(Sock sock, int quantity);

    int remove(Sock sock, int quantity);

    int getMin(Sock sock);

    int getMax(Sock sock);
}

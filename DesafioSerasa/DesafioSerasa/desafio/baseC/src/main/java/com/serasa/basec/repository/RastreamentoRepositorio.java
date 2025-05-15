package com.serasa.basec.repository;

import com.serasa.basec.model.HistoricoEvento;
import com.serasa.basec.service.RastreamentoService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Repository
public class RastreamentoRepositorio {

    private final RedisTemplate<String, HistoricoEvento> redisTemplate;
    private static final String KEY_PREFIX = "tracking:";
    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    public RastreamentoRepositorio(RedisTemplate<String, HistoricoEvento> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void adicionarEvento(String cpf, HistoricoEvento event, Duration ttl) {
        String key = KEY_PREFIX + cpf;
        redisTemplate.opsForList().rightPush(key, event);
        redisTemplate.expire(key, ttl);
    }

    public HistoricoEvento getUltimoEvento(String cpf, String eventType) {
        String key = KEY_PREFIX + eventType + ":" + cpf;
        return redisTemplate.opsForList().index(key, -1);
    }
}
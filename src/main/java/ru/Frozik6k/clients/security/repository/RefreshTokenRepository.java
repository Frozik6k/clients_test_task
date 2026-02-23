package ru.Frozik6k.clients.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.Frozik6k.clients.security.model.RefreshToken;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByTokenHash(String tokenHash);

    @Modifying
    @Query("""
        update RefreshToken rt
        set rt.revokedAt = :now
        where rt.userId = :userId
            and rt.revokedAt is null
            and rt.expiresAt > :now
    """)
    int revokeAllActiveByUserId(@Param("userId") UUID userId, @Param("now") Instant now);
}

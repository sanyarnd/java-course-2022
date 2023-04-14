package com.example.jpaworkshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
public class Game {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_first_id")
    private Team teamFirst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_second_id")
    private Team teamSecond;

    @Column(name = "team_first_points")
    private int teamFirstPoints;

    @Column(name = "team_second_points")
    private int teamSecondPoints;

    @ManyToOne(fetch = FetchType.LAZY)
    private Player mvp;

    @Version
    @Column(name = "version")
    private Integer version;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @CreationTimestamp
    @Column(name = "created_date")
    private Instant createdDate;

}

package com.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Infrastructure {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "availability")
    private Boolean availability;

    private String description;

    private String coordinates;

    @ManyToMany(mappedBy = "infrastructures", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @Setter(AccessLevel.PRIVATE)
    private Set<Attribute> attributes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Type type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Infrastructure that = (Infrastructure) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (availability != null ? !availability.equals(that.availability) : that.availability != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (availability != null ? availability.hashCode() : 0);
        return result;
    }
}

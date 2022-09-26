package be.pxl.boardgame.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="boardgame")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}

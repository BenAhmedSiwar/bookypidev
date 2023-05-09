package tn.esprit.spring.spring11.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tn.esprit.spring.spring11.interfaces.CommentView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString
@JsonView(CommentView.Minimal.class)
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @JsonIgnoreProperties({"publications", "ratings"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"comments"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id")
    private BookPublication publication;

    @Column(name = "moderated", columnDefinition = "boolean default false")
    private boolean moderated;

    public void filterBadWords(List<String> badWords) {
        if (text == null) {
            return;
        }

        for (String word : badWords) {
            String regex = "\\b" + Pattern.quote(word) + "\\b";
            String replacement = "";
            for (int i = 0; i < word.length(); i++) {
                replacement += "*";
            }
            text = text.replaceAll(regex, replacement);
        }

    }
    // getters and setters
}

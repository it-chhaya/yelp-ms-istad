package kh.edu.cstad.idenity.domain;

import jakarta.persistence.*;
import kh.edu.cstad.idenity.config.jpa.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subscribers")
public class Subscriber extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user; // userId after saving into database

    private String nickName; // MR. CHAN CHHAYA (iLJiMae)

    private String headline; // some interesting bio that shows on the top of profile

    private String iLove; // (e.g. sushi, Radiohead, puppies)

    private String findMeIn; // Nob Hill, the newest brunch spot, a turtleneck

    private String hometown; // Schenectady, NY

    private String jobTitle; // Software Developer, Accountant Assistant

    private String blogOrWebsiteUrl; // www.example.com/myawesomeblog

    private String whyYouShouldReadMyReview; // They’re useful, funny, and cool; I tell it like it is; I eat out all the time

    private String mySecondFavoriteWebsite; // www.picturesofcats.com, www.wikipedia.com

    private String lastBookIRead; // The Joy Of Cooking; The Lord of the Ring: The Return of the King

    private String myFirstConcert; // Beyoncé, Radiohead, Run DMC

    private String myFavoriteMovie; // Avenger: EndGame, It’s a Wonderful Life, Spirited Away

    private String currentCrush; // MS.MOM REKSMEY

}

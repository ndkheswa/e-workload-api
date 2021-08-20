package com.learncoding.eworkloadjava.model;

        import com.fasterxml.jackson.annotation.*;
        import com.sun.istack.NotNull;
        import lombok.Data;

        import javax.persistence.*;
        import java.util.Date;

@Data
@Entity
public class Booking extends AuditModel {

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="api_seq", initialValue=20583748, allocationSize=50)
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
    private Long id;

    @NotNull
    private String title;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @NotNull Date bookingDate;

    @NotNull
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JoinColumn(name = "client_id", nullable = false)
    @JsonProperty("client_id")
    private Client client;
}

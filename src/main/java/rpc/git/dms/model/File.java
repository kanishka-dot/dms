package rpc.git.dms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "dms_files")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {

    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private String recordId;
    @Column(nullable = false,length = 100)
    private String fileName;
    @Column(length = 25)
    private String fileExt;
    @Column(nullable = false)
    private Long fileSize;
    @Column(nullable = false)
    private String version;
    @Column(nullable = false, columnDefinition="MEDIUMBLOB")
    @Lob
    private byte[] data;
    private Date createDate;
    private Date modDate;
}

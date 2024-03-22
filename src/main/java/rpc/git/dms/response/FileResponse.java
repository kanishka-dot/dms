package rpc.git.dms.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {

    private String fileName;
    private String url;
    private String fileType;
    private long fileSize;

}

package rpc.git.dms.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import rpc.git.dms.model.File;

public interface FileRepo extends JpaRepository<File,String> {
}

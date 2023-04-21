package com.pcPartMaker.repository;
import javax.persistence.ParameterMode;
import com.pcPartMaker.model.Motherboard;
import com.pcPartMaker.model.RamKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.core.env.Environment;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import javax.persistence.StoredProcedureQuery;
public interface RamKitRepository extends JpaRepository<RamKit, Integer> {

    @Query("SELECT modelName FROM RamKit")
    List<String> findAllRams();

    Optional<RamKit> findByModelNumber(Integer ramId);

    //@Query(value = "CALL motherboard_ram_compatibility(:motherboard_id, :ramKit_id);", nativeQuery = true)
    @Procedure(value = "motherboard_ram_compatibility")
    boolean checkMotherboardRam(@Param("motherboard_id") Integer motherboard_id, @Param("ramKit_id")String ramKit_id);

    @Procedure(value = "motherboard_gpu_compatibility")
    boolean checkMotherboardGPU(@Param("motherboard_id") Integer motherboard_id, @Param("graphics_card_id")String graphics_card_id);
    @Procedure(value = "motherboard_cpu_compatiblity")
    boolean checkMotherboardCPU(@Param("motherboard_id") Integer motherboard_id, @Param("cpu_id")Integer cpu_id);
    @Procedure(value = "cpu_ram_compatibility")
    boolean checkCpuRam(@Param("cpu_id") Integer cpu_id, @Param("ramKit_id")String ramKit_id);
//
//    public boolean checkMotherboardRam (Integer motherboard_id, String ramKit_id) {
//        String dbName = env.getProperty("spring.jpa.properties.hibernate.default_schema");
//        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("pc_part_maker"
//                + ".motherboard_ram_compatibility");
//
//        query.registerStoredProcedureParameter("motherboard_id", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("ramKit_id", String.class, ParameterMode.IN);
//
//        query.registerStoredProcedureParameter("Result_Param", Boolean.class, ParameterMode.OUT);
//
//        query.setParameter("motherboard_id", motherboard_id);
//        query.setParameter("ramKit_id", ramKit_id);
//
//        boolean count = (boolean) query.getOutputParameterValue("Result_Param");
//
//        return count;
    }



package com.pcPartMaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name="motherboard_chipset")
public class MotherboardChipset {
    @Id
    String chipset_model;
    @ManyToMany(mappedBy = "motherboardChipset")
    private Collection<Cpu> compatible_cpu;

    public Collection<Cpu> getChipset_name() {
        return compatible_cpu;
    }

    public void setChipset_name(Collection<Cpu> chipset_name) {
        this.compatible_cpu = chipset_name;
    }
}

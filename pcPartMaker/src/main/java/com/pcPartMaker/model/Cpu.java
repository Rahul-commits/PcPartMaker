package com.pcPartMaker.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="cpu")
public class Cpu {
    @Id
    private String model_number;
    @Column(unique = true)
    private String model_name;
    private String generation_name;
    private short number_of_cores;
    private String architecture;
    private Boolean ecc_compatibility;
    private float clock_speed;
    private short TDP;
    private short wattage;


    @ManyToOne
    @JoinColumn(name = "manufacturer_name")
    private CpuManufacturer cpuManufacturer;


    @ManyToOne
    @JoinColumn(name = "socket")
    private SocketType socketType;

    @ManyToMany
    @JoinColumn(name = "chipset_name")
    private Collection<MotherboardChipset> motherboardChipset;

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public short getNumber_of_cores() {
        return number_of_cores;
    }

    public void setNumber_of_cores(short number_of_cores) {
        this.number_of_cores = number_of_cores;
    }

    public String getGeneration_name() {
        return generation_name;
    }

    public void setGeneration_name(String generation_name) {
        this.generation_name = generation_name;
    }

    public Boolean getEcc_compatibility() {
        return ecc_compatibility;
    }

    public void setEcc_compatibility(Boolean ecc_compatibility) {
        this.ecc_compatibility = ecc_compatibility;
    }

    public float getClock_speed() {
        return clock_speed;
    }

    public void setClock_speed(float clock_speed) {
        this.clock_speed = clock_speed;
    }

    public short getTDP() {
        return TDP;
    }

    public void setTDP(short TDP) {
        this.TDP = TDP;
    }

    public short getWattage() {
        return wattage;
    }

    public void setWattage(short wattage) {
        this.wattage = wattage;
    }

    public CpuManufacturer getCpuManufacturer() {
        return cpuManufacturer;
    }

    public void setCpuManufacturer(CpuManufacturer cpuManufacturer) {
        this.cpuManufacturer = cpuManufacturer;
    }

    public SocketType getSocketType() {
        return socketType;
    }

    public void setSocketType(SocketType socketType) {
        this.socketType = socketType;
    }
}

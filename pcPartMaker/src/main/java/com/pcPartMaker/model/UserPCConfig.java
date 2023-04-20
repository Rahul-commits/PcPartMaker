package com.pcPartMaker.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
@Data
@Setter
@Getter
public class UserPCConfig implements Serializable {

    private List<CPU> cpus;
    private List<RamKit> rams;
    private List<Motherboard> motherboards;

    private List<GraphicsCard> graphicsCards;
    private List<PCConfiguration> pcConfigurationList;

    private Boolean isCorrectConfig;

    private Integer newPCConfigId;
    public UserPCConfig(List<CPU> cpus, List<RamKit> rams, List<Motherboard> motherboards,
                        List<GraphicsCard> graphicsCards, List<PCConfiguration> pcConfigurationList,
                        Boolean isCorrectConfig, Integer newPCConfigId) {
        this.cpus = cpus;
        this.rams = rams;
        this.motherboards = motherboards;
        this.pcConfigurationList = pcConfigurationList;
        this.graphicsCards = graphicsCards;
        this.isCorrectConfig = isCorrectConfig;
        this.newPCConfigId = newPCConfigId;
    }

    public List<CPU> getCpus() {
        return cpus;
    }

    public List<RamKit> getRams() {
        return rams;
    }

    public List<Motherboard> getMotherboards() {
        return motherboards;
    }

    public List<PCConfiguration> getPcConfigurationList() {
        return pcConfigurationList;
    }

    public List<GraphicsCard> getGraphicsCards() {
        return graphicsCards;
    }

    public Boolean getCorrectConfig() {
        return isCorrectConfig;
    }

    public Integer getNewPCConfigId() {
        return newPCConfigId;
    }
}

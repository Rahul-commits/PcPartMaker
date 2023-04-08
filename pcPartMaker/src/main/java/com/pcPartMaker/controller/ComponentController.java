package com.pcPartMaker.controller;


import com.pcPartMaker.model.CPU;
import com.pcPartMaker.model.GraphicsCard;
import com.pcPartMaker.model.Motherboard;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/component")
public class ComponentController {

    // Post mapping

    @PostMapping("/graphicsCard")
    public GraphicsCard createGraphicsCard(@RequestBody GraphicsCard card){
        return null;
    }

    @PostMapping("/cpu")
    public CPU createCpu(@RequestBody CPU cpu){
        return null;
    }


    @PostMapping("/psu")
    public String createPSU(@RequestBody String cpu){
        return "PSU";
    }

    @PostMapping("/ramkit")
    public String createRamKit(@RequestBody String ramKit){
        return ramKit;
    }

    @PostMapping("/motherboard")
    public Motherboard createMotherboard(@RequestBody Motherboard motherboard){
        return motherboard;
    }



    //GET MAPPINGS

    @GetMapping("/graphicsCard/{model}")
    public GraphicsCard getGraphicsCard(@PathVariable String model){
        return null;
    }

    @GetMapping("/cpu/{model}")
    public CPU getCPU(@PathVariable String model){
        return null;
    }


    @GetMapping("/psu/{model}")
    public String getPSU(@PathVariable String model){
        return "PSU";
    }

    @GetMapping("/ramkit/{model}")
    public String getRam(@PathVariable String model){
        return model;
    }

    @GetMapping("/motherboard/{model}")
    public String getMotherboard(@PathVariable String model){
        return model;
    }

}

package com.artere.shop;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModulithTest {

    ApplicationModules modules = ApplicationModules.of(ArtereShopApplication.class);

    @Test
    void verify_modulith_structure() {
        modules.verify();
    }

    @Test
    void write_documentation() {
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }
}

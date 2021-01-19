package com.gui;

import com.client.ClientControl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@Component
public class SearchPanel {
    @Autowired
    private ClientControl clientControl;

}

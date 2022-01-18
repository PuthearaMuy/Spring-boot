package com.springboot.theara.service;

import java.util.List;

public interface Service {
    public List selectAll();
    public String deleteAll();
    public String deleteById(Long id);
}

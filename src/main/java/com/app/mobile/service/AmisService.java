package com.app.mobile.service;

import com.app.mobile.dto.AmisDto;
import com.app.mobile.model.Amis;

import java.util.List;
import java.util.Map;

public interface AmisService {
    public Amis insertAmis(AmisDto amisDto);
    public List<Amis> getAllAmis();
    public Amis updateValiderAmis(int id);
    public Amis updateStatutAmis(int id);
    public String deleteAmis(int id);
    public List<Map<String, Object>> getAmisCommun(int id1 , int id2);
    public List<Map<String, Object>> getAmisUser(int id);
    public List<Map<String, Object>> getAmisDemande(int id);
    public List<Map<String, Object>> getNotifAmisValider(int id);
}

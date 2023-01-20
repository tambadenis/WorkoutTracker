package com.tambadenis.WorkoutTracker.service;

import com.tambadenis.WorkoutTracker.model.Set;
import com.tambadenis.WorkoutTracker.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetService {
    @Autowired
    private SetRepository setRepository;

    public List<Set> getAllSets() {
        return setRepository.findAll();
    }

    public Set getSetsById(Long id) {
        return setRepository.findById(id).orElse(null);
    }

    public void createSet(List<Set> sets) {
        setRepository.saveAll(sets);
    }

    public Set updateSet(Long id, Set set) {
        Set existingSet = setRepository.findById(id).orElse(null);
        if (existingSet != null) {
            existingSet.setSetNumber(set.getSetNumber());
            existingSet.setNumberOfReps(set.getNumberOfReps());
            return setRepository.save(existingSet);
        } else {
            return null;
        }
    }
    public void deleteSet(Long id) {
        setRepository.deleteById(id);
    }

    public void saveSet(Set set) {
        setRepository.save(set);
    }
}


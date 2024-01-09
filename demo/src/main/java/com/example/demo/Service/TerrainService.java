package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.TerrainRepository;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Terrain;

@Service
public class TerrainService {
	
	@Autowired
	TerrainRepository terrainRepository;

	public <S extends Terrain> S save(S entity) {
		return terrainRepository.save(entity);
	}

	public List<Terrain> findAll() {
		return terrainRepository.findAll();
	}

	public Optional<Terrain> findById(Integer id) {
		return terrainRepository.findById(id);
	}

	public void deleteById(Integer id) {
		terrainRepository.deleteById(id);
	}
	
	public List<Object[]> getNomByTerrainId(int terrainId) {
        return terrainRepository.getNomByTerrainId(terrainId);
    }
	
	public List<Object[]> getTypeByTerrainId(int terrainId){
		return terrainRepository.getTypeByTerrainId(terrainId);
	}
	
	public ResponseEntity<Terrain> updateTerrain(int id, Terrain terrain) {
	    Optional<Terrain> optionalTerrain = terrainRepository.findById(id);

	    if (optionalTerrain.isPresent()) {
	        Terrain existingTerrain = optionalTerrain.get();
	        existingTerrain.setMc(terrain.getMc());
	        existingTerrain.setProprietaire(terrain.getProprietaire());
	        existingTerrain.setCategorie(terrain.getCategorie());

	        Terrain updatedTerrain = terrainRepository.save(existingTerrain);
	        return ResponseEntity.ok(updatedTerrain);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
}

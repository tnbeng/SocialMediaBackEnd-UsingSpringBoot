package in.tnb.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.tnb.main.models.Reels;

public interface ReelsRepo extends JpaRepository<Reels, Integer>{
     public List<Reels> findByUserUserid(int userid);
}

package com.jinnjartech.posnip.Service;

import com.jinnjartech.posnip.Model.Code;
import com.jinnjartech.posnip.Model.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CodeService {
    CodeRepository repository;

    @Autowired
    CodeService(CodeRepository repository) {
        this.repository = repository;
    }

    public void addCode(Code code) {
        repository.save(code);
    }

    public Code getCode(String uuid) {
        return repository.findByUuid(uuid);
    }

    public List<Code> getLatest() {
        return repository.findTop10ByTimeLessThanEqualAndViewsLessThanEqualOrderByRealDateDesc(0, 0);
    }

    public void removeCode(String uuid) {
        repository.deleteByUuid(uuid);
    }

    public long timeElapsed(Code code) {
        return LocalDateTime.now()
                .until(code.getUnformattedDate().plusSeconds(code.getTime()), ChronoUnit.SECONDS);
    }
}

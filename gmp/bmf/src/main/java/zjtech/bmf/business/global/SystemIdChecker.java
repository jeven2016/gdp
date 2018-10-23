/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.business.global;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class SystemIdChecker {

    public Serializable getCurrentID(Serializable id) {
        if (id instanceof Long) {
            return id;
        }

        if (id instanceof String) {
            Long idLong = Long.valueOf((String) id);
            return idLong;
        }
        throw new IllegalArgumentException("Invalid id (" + id + ").");
    }
}

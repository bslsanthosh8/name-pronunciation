package com.wellsfargo.name.pronunciation.service.impl;

import com.wellsfargo.name.pronunciation.entity.Employee;
import com.wellsfargo.name.pronunciation.entity.EmployeeAvatar;
import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;
import com.wellsfargo.name.pronunciation.repository.EmployeeAvatarRepository;
import com.wellsfargo.name.pronunciation.service.EmployeeAvatarService;
import com.wellsfargo.name.pronunciation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAvatarServiceImpl implements EmployeeAvatarService {

    @Autowired
    EmployeeAvatarRepository employeeAvatarRepository;

    @Autowired
    EmployeeService employeeService;

    @Override
    public EmployeeAvatar getEmployeeAvatar(String uid) throws NamePronunciationException {
        Employee employee = employeeService.getEmployee(uid);
        return employeeAvatarRepository.findById(employee.getUid()).orElse(new EmployeeAvatar());
    }

    @Override
    public void addEmployeeEmployeeAvatar(String uid, byte[] avatarImage) throws NamePronunciationException {

        Employee employee = employeeService.getEmployee(uid);
        EmployeeAvatar employeeAvatar = new EmployeeAvatar();
        employeeAvatar.setUid(employee.getUid());
        employeeAvatar.setImage(avatarImage);
        employeeAvatarRepository.save(employeeAvatar);
    }

    @Override
    public void deleteEmployeeAvatar(String uid) {
        employeeAvatarRepository.deleteById(uid);
    }
}

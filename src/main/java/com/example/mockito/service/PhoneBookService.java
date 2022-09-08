package com.example.mockito.service;

import com.example.mockito.dao.PhoneBookRepository;

/**
 * <p>在开始处详细描述该类的作用</p>
 * <p>TODO</p>
 *
 * @author Jiahui.Huang
 * @date 2022/9/8 11:49
 * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
public class PhoneBookService {
    private PhoneBookRepository phoneBookRepository;

    public PhoneBookService(PhoneBookRepository phoneBookRepository) {
        this.phoneBookRepository = phoneBookRepository;
    }

    public void register(String name, String phone) {
        if(!name.isEmpty() && !phone.isEmpty()
                && !phoneBookRepository.contains(name)) {
            phoneBookRepository.insert(name, phone);
        }
    }

    public String search(String name) {
        if(!name.isEmpty() && phoneBookRepository.contains(name)) {
            return phoneBookRepository.getPhoneNumberByContactName(name);
        }
        return null;
    }
}
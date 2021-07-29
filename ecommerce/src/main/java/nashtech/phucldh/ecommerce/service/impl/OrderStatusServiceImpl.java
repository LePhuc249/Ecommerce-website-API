package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.OrderStatusConverter;
import nashtech.phucldh.ecommerce.dto.OrderStatus.OrderStatusDTO;
import nashtech.phucldh.ecommerce.entity.OrderStatus;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.repository.OrderStatusRepository;
import nashtech.phucldh.ecommerce.service.OrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStatusServiceImpl.class);

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    OrderStatusConverter orderStatusConverter;

    @Override
    public List<OrderStatusDTO> findAllStatus() throws DataNotFoundException {
        List<OrderStatusDTO> listDTO;
        try {
            List<OrderStatus> theListOrderStatus = orderStatusRepository.findAll();
            if (theListOrderStatus.size() > 0) {
                listDTO = new ArrayList<>();
                for (OrderStatus status: theListOrderStatus) {
                    OrderStatusDTO dto = orderStatusConverter.convertOrderStatusToDTO(status);
                    listDTO.add(dto);
                }
            } else {
                LOGGER.info(ErrorCode.ERR_ORDER_STATUS_LIST_EMPTY);
                throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_LIST_EMPTY);
            }
        } catch (Exception e) {
            LOGGER.info("Having error when loading order status list: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public OrderStatusDTO getOrderStatusById(Long idStatus) throws DataNotFoundException {
        OrderStatusDTO dto;
        try {
            Optional<OrderStatus> result = orderStatusRepository.findById(idStatus);
            OrderStatus theOrderStatus;
            if (result.isPresent()) {
                theOrderStatus = result.get();
            } else {
                LOGGER.info("Can't find status by id " + idStatus);
                throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
            }
            dto = orderStatusConverter.convertOrderStatusToDTO(theOrderStatus);
        } catch (Exception e) {
            LOGGER.info("Having error when loading order status: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_LOADED_FAIL);
        }
        return dto;
    }

    @Override
    public Boolean createNewOrderStatus(OrderStatusDTO dto) throws CreateDataFailException {
        boolean result;
        try {
            OrderStatus status = orderStatusConverter.convertOrderStatusDTOToEntity(dto);
            status.setCreateDate(LocalDateTime.now());
            orderStatusRepository.save(status);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error to create status: " + e.getMessage());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORDER_STATUS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateOrderStatus(OrderStatusDTO dto) throws UpdateDataFailException {
        boolean result;
        try {
            Optional<OrderStatus> optionalStatus = orderStatusRepository.findById(dto.getId());
            if (!optionalStatus.isPresent()) {
                LOGGER.info("Can't find status with status id: " + dto.getId());
                throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
            }
            OrderStatus status = orderStatusConverter.convertOrderStatusDTOToEntity(dto);
            status.setCreateDate(optionalStatus.get().getCreateDate());
            orderStatusRepository.save(status);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error to update status: " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteOrderStatus(Long idStatus) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        try {
            Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(idStatus);
            OrderStatus theOrderStatus;
            if (optionalOrderStatus.isPresent()) {
                theOrderStatus = optionalOrderStatus.get();
            } else {
                LOGGER.info("Can't find status by id " + idStatus);
                throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
            }
            orderStatusRepository.deleteOrderStatus(theOrderStatus.getId());
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when delete status " + e.getMessage());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORDER_STATUS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean unDeleteOrderStatus(Long idStatus) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        try {
            Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(idStatus);
            OrderStatus theOrderStatus;
            if (optionalOrderStatus.isPresent()) {
                theOrderStatus = optionalOrderStatus.get();
            } else {
                LOGGER.info("Can't find status by id " + idStatus);
                throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
            }
            orderStatusRepository.unDeleteOrderStatus(theOrderStatus.getId());
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when update status  " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
        }
        return result;
    }

}
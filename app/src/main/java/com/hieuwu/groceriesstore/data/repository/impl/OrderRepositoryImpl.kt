package com.hieuwu.groceriesstore.data.repository.impl

import com.hieuwu.groceriesstore.data.database.dao.LineItemDao
import com.hieuwu.groceriesstore.data.database.dao.OrderDao
import com.hieuwu.groceriesstore.data.database.entities.LineItem
import com.hieuwu.groceriesstore.data.database.entities.Order
import com.hieuwu.groceriesstore.data.database.entities.asDomainModel
import com.hieuwu.groceriesstore.data.network.dto.OrderDto
import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.utilities.CollectionNames
import com.hieuwu.groceriesstore.utilities.OrderStatus
import com.hieuwu.groceriesstore.utilities.SupabaseMapper
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val lineItemDao: LineItemDao,
    private val postgrest: Postgrest
) : OrderRepository {

    override suspend fun createOrUpdate(order: Order) {
        try {
            orderDao.insert(order)
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }

    override suspend fun addLineItem(lineItem: LineItem) {
        try {
            lineItemDao.insert(lineItem)
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }

    override fun getOneOrderByStatus(status: OrderStatus): Flow<OrderModel?> {
        return try {
            return orderDao.getCartWithLineItems(status.value).map {
                it?.asDomainModel()
            }
        } catch (e: Exception) {
            Timber.e(e.message)
            flow {}
        }
    }

    override suspend fun sendOrderToServer(order: OrderModel): Boolean {
        val orderDto = SupabaseMapper.mapModelToDto(order)
        val lineItems = SupabaseMapper.mapModelListToDto(order)
        return try {
            postgrest[CollectionNames.orders].insert(orderDto)
            postgrest[CollectionNames.lineItems].insert(lineItems)
            orderDao.clear()
            true
        } catch (e: Exception) {
            Timber.e(e.message)
            false
        }
    }

    override suspend fun getOrders(): List<OrderModel> {
        return try {
            val result = postgrest[CollectionNames.orders].select().decodeList<OrderDto>()
            result.map { SupabaseMapper.mapToModel(it) }
        } catch (e: Exception) {
            listOf()
        }
    }
}

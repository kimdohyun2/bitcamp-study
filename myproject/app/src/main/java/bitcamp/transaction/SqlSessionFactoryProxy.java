package bitcamp.transaction;

import org.apache.ibatis.session.*;

import java.sql.Connection;

public class SqlSessionFactoryProxy implements SqlSessionFactory {

    private SqlSessionFactory originalFactory;

    private ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<>();

    public SqlSessionFactoryProxy(SqlSessionFactory originalFactory) {
        this.originalFactory = originalFactory;
    }

    public void clearSession() {
        sqlSessionThreadLocal.remove();
    }

    @Override
    public SqlSession openSession() {
        SqlSession sqlSession = sqlSessionThreadLocal.get();
        if (sqlSession == null) {
            sqlSession = new SqlSessionFroxy(originalFactory.openSession());
            sqlSessionThreadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
        return originalFactory.openSession(autoCommit);
    }

    @Override
    public SqlSession openSession(Connection connection) {
        return null;
    }

    @Override
    public SqlSession openSession(TransactionIsolationLevel level) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, Connection connection) {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
